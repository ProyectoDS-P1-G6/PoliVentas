package controllers;


import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Pedido;
import models.entities.Administrador;
import models.entities.Comprador;
import models.entities.Rol;
import models.entities.Usuario;
import services.AdministradorServiceDB;
import services.CompradorServiceDB;
import utils.StageDecoratorX;
import views.CrearUsuario;
import views.MenuAdministrador;
import views.items.ArticuloItem;
import views.items.PedidoItem;
import views.items.UserItem;
import views.popup.RealizarCompraController;

public final class MenuAdministradorController{
    LoginController login;
    CrearUsuario ventanaRegistro = new CrearUsuario();
    private Administrador administrador;
    private MenuAdministrador menuAdministrador;
    private UserItem viewUserItem;
    private AdministradorServiceDB db;
    

    public MenuAdministradorController(Administrador modelo, MenuAdministrador view) {
        this.administrador = modelo;
        this.menuAdministrador = view;
        db = new AdministradorServiceDB();
        viewUserItem = new UserItem();
        //ventanaRegistro =  new Stage();
        
        
        
        view.buscarAction((EventHandler<ActionEvent>) new buscarOperation() );
        view.productosAction((EventHandler<ActionEvent>) new admProductOperation());
        view.usersAction((EventHandler<ActionEvent>) new admUserOperation());
        view.compraAction((EventHandler<ActionEvent>) new admCompraOperation());
        view.createButtonUserAction(new crearUsuarioButton());
        ventanaRegistro.btnCrearRegistro((EventHandler<ActionEvent>) new eventoBtnRegistroUsuario());
        
        cargarPedidos();
        cargarUsuarios();
        obtenerDatosPersonales();
    }
    Usuario crearUsuario(Usuario nuevoUsuario){
        nuevoUsuario.setNombres(ventanaRegistro.getNombre().getText());
        nuevoUsuario.setApellidos(ventanaRegistro.getApellidos().getText());
        nuevoUsuario.setCedula(Integer.parseInt(ventanaRegistro.getCedula().getText()));
        String value = ventanaRegistro.getWhatsapp().getValue();
        nuevoUsuario.setContactInfo(ventanaRegistro.getEmail().getText(),Integer.parseInt(ventanaRegistro.getTelefono().getText()),ventanaRegistro.parseBooleanWhatsapp(value));
        nuevoUsuario.setDireccion(ventanaRegistro.getDireccion().getText());
        nuevoUsuario.setApellidos(ventanaRegistro.getApellidos().getText());
        nuevoUsuario.setMatricula(Integer.parseInt(ventanaRegistro.getMatricula().getText()));
        nuevoUsuario.setRol(ventanaRegistro.getPerfil().getValue());
        
        return nuevoUsuario;
    }
    
    void obtenerDatosPersonales(){
        List<String> dataAdmin = db.getDatosUsuario(administrador);
        for(String o:dataAdmin){
            menuAdministrador.AsignarDatosAdministardor(o);
        }
    }
    void cargarUsuarios(){
        List<Usuario> usuarios =  db.getUsuarios();
        for(Usuario u: usuarios){
            System.out.println(u);
            UserItem userView = new UserItem(u);
            menuAdministrador.chargerUsuarios(userView);
        }
    }
    void cargarPedidos(){
        List<Pedido> pedidos =  db.getPedidos(administrador);
        
        
        for(Pedido p: pedidos){
            System.out.println(p);
            PedidoItem item = new PedidoItem(p);
            switch(p.getEstado()){
                case PENDIENTE:
                    menuAdministrador.chargerPedidosPendientes(item);
                    break;
                case ENTREGADO:
                    menuAdministrador.chargerPedidosExitosos(item);
                    break;
                case ANULADO:
                    menuAdministrador.chargerPedidosAnulados(item);
            }
            //item.setOnMouseClicked(new MenuAdministradorController.OnPedidoSelected(item));
        }
    }
    class buscarOperation implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
             System.out.println("ABRIR BUSQUEDA");
             menuAdministrador.changeViewAdmin("BUSCAR");
            }
        }   
    class admUserOperation implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
             System.out.println("ABRIR ADMINISTRACION DE USUARIOS");
             menuAdministrador.changeViewAdmin("USERS");
            }
        }   
    class admProductOperation implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
             System.out.println("ABRIR ADMINISTRAR PRODUCTOS");
             menuAdministrador.changeViewAdmin("PRODUCTO");
            }
        }   
    class admCompraOperation implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
             System.out.println("ABRIR ADMINISTRAR COMPRAS");
             menuAdministrador.changeViewAdmin("COMPRA");
            }
        } 
    
    public void setLoginController(LoginController login) {
        this.login = login;
    }
    
    
    
    class crearUsuarioButton implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent actionEvent) {
            if(!ventanaRegistro.isShowing()){
                System.out.println("registrarse....");
                new StageDecoratorX(ventanaRegistro);
                ventanaRegistro.show();
               
            }else{
                System.out.println("CULMINE EL ACTUAL REGISTRO");
            }
        }
    }
     class eventoBtnRegistroUsuario implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
            
             db.createUser(crearUsuario(new Usuario()));
             System.out.println("USUARIO INSERTADO CON EXITO");
             
            }
    }
}
package controllers;


import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.Pedido;
import models.entities.Administrador;
import models.entities.Usuario;
import services.AdministradorServiceDB;
import utils.StageDecoratorX;
import views.CrearUsuario;
import views.MenuAdministrador;
import views.items.PedidoItem;
import views.items.UserItem;

public final class MenuAdministradorController{
    LoginController login;
    CrearUsuario ventanaRegistro = new CrearUsuario();
    private Administrador administrador;
    private MenuAdministrador menuAdministrador;
    private AdministradorServiceDB db;
    ArrayList<UserItem> listUser;
    UserItem userView;

    public MenuAdministradorController(MenuAdministrador view) {
        this.menuAdministrador=view;
        db = new AdministradorServiceDB();
    }
    
    public MenuAdministradorController(Administrador modelo, MenuAdministrador view) {
        this.administrador = modelo;
        this.menuAdministrador = view;
        db = new AdministradorServiceDB();
        
        menuAdministrador.buscarAction((EventHandler<ActionEvent>) new buscarOperation() );
        menuAdministrador.productosAction((EventHandler<ActionEvent>) new admProductOperation());
        menuAdministrador.usersAction((EventHandler<ActionEvent>) new admUserOperation());
        menuAdministrador.compraAction((EventHandler<ActionEvent>) new admCompraOperation());
        menuAdministrador.createButtonUserAction(new crearUsuarioButton());
        menuAdministrador.actualizarBtnAction(new actualizarVistaButton());
        
        ventanaRegistro.btnCrearRegistro((EventHandler<ActionEvent>) new eventoBtnGuardarNuevoUsuario());
        ventanaRegistro.btnLimpiarRegistro((EventHandler<ActionEvent>) new eventoBtnLimpiarRegistro());
        
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
    void eliminarUsuario(Usuario killUser){
        
    }
    void obtenerDatosPersonales(){
        List<String> dataAdmin = db.getDatosUsuario(administrador);
        for(String o:dataAdmin){
            menuAdministrador.AsignarDatosAdministardor(o);
        }
    }
    public void cargarUsuarios(){
        List<Usuario> usuarios =  db.getUsuarios();
        for(Usuario u: usuarios){
            //System.out.println(u);
            userView = new UserItem(u);
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
             menuAdministrador.changeViewAdmin("BUSCAR");
            }
        }   
    class admUserOperation implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
             menuAdministrador.changeViewAdmin("USERS");
            }
        }   
    class admProductOperation implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
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
    
    
    class actualizarVistaButton implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent actionEvent) {
            menuAdministrador.clearPanelUser();
            cargarUsuarios();
        }
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
     class eventoBtnGuardarNuevoUsuario implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
            
             db.createUser(crearUsuario(new Usuario()));
             ventanaRegistro.limpiarCampos();
             ventanaRegistro.close();
             menuAdministrador.clearPanelUser();
             cargarUsuarios();
             
             System.out.println("USUARIO INSERTADO CON EXITO");
             
            }
    }
    class eventoBtnLimpiarRegistro implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
             ventanaRegistro.limpiarCampos();
        }
    }

}

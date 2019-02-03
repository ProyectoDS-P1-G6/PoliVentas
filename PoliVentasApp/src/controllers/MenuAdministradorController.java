package controllers;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javax.mail.internet.AddressException;
import models.Pedido;
import models.entities.Administrador;
import models.entities.Usuario;
import services.AdministradorServiceDB;
import utils.Returnable;
import utils.StageDecoratorX;
import views.CrearUsuario;
import views.MenuAdministrador;
import views.items.PedidoItem;
import views.items.UserItem;

public final class MenuAdministradorController implements Returnable{
    LoginController login;
    CrearUsuario ventanaRegistro = new CrearUsuario();
    Returnable previousWindows;
    
    
    private final Administrador administrador;
    private final MenuAdministrador menuAdministrador;
    private final UserItem viewUserItem;
    private final AdministradorServiceDB db;
    

    public MenuAdministradorController(Administrador modelo, MenuAdministrador view) {
        this.administrador = modelo;
        this.menuAdministrador = view;
        db = new AdministradorServiceDB();
        viewUserItem = new UserItem();
        //ventanaRegistro =  new Stage();
        
        view.buscarAction(new buscarOperation() );
        view.productosAction( new admProductOperation());
        view.usersAction(new admUserOperation());
        view.compraAction(new admCompraOperation());
        view.createButtonUserAction(new crearUsuarioButton());
        ventanaRegistro.btnCrearRegistro( new eventoBtnRegistroUsuario());
        
        cargarPedidos();
        cargarUsuarios();
        //obtenerDatosPersonales();
    }
    
    
    Usuario crearUsuario(){
        
        Usuario nuevoUsuario = Usuario.createUserByRol(ventanaRegistro.getPerfil().getValue());
        
        nuevoUsuario.setNombres(ventanaRegistro.getNombre().getText());
        nuevoUsuario.setApellidos(ventanaRegistro.getApellidos().getText());
        nuevoUsuario.setCedula(Integer.parseInt(ventanaRegistro.getCedula().getText()));
        String value = ventanaRegistro.getWhatsapp().getValue();
        try {
            nuevoUsuario.setContactInfo(ventanaRegistro.getEmail().getText(),Integer.parseInt(ventanaRegistro.getTelefono().getText()),ventanaRegistro.parseBooleanWhatsapp(value));
        } catch (AddressException ex) {
            Logger.getLogger(MenuAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nuevoUsuario.setDireccion(ventanaRegistro.getDireccion().getText());
        nuevoUsuario.setApellidos(ventanaRegistro.getApellidos().getText());
        nuevoUsuario.setMatricula(Integer.parseInt(ventanaRegistro.getMatricula().getText()));

        
        return nuevoUsuario;
    }
    /*
    void obtenerDatosPersonales(){
        List<String> dataAdmin = db.getDatosUsuario(administrador);
        for(String o:dataAdmin){
            menuAdministrador.AsignarDatosAdministardor(o);
        }
    }*/
    
    
    
    void cargarUsuarios(){
        List<Usuario> usuarios =  db.getUsuarios();
        for(Usuario u: usuarios){
            System.out.println(u);
            UserItem userView = new UserItem(u);
            menuAdministrador.chargerUsuarios(userView);
        }
    }
    void cargarPedidos(){
        List<Pedido> pedidos =  db.getPedidosPendientes(administrador);
        
        
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
            
             db.createUser(crearUsuario());
             System.out.println("USUARIO INSERTADO CON EXITO");
             
            }
    }
     
     
    @Override
    public void setPreviusWindow(Returnable previous) {
        this.previousWindows = previous;
    }

    @Override
    public void showWindow() {
        menuAdministrador.show();
    }
}
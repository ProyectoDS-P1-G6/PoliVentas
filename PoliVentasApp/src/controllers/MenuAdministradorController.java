package controllers;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javax.mail.internet.AddressException;
import models.Articulo;
import models.Pedido;
import models.entities.Administrador;
import models.entities.Usuario;
import services.AdministradorServiceDB;
import utils.Returnable;
import utils.StageDecoratorX;
import views.CrearUsuario;
import views.MenuAdministrador;
import views.items.ArticuloItem;
import views.items.ArticuloItemAdm;
import views.items.PedidoItem;
import views.items.UserItem;

public final class MenuAdministradorController extends MenuVendedorController implements Returnable{
    LoginController login;
    CrearUsuario ventanaRegistro = new CrearUsuario();
    Returnable previousWindows;
    
    
    private final Administrador administrador;
    private final MenuAdministrador menuAdministrador;
    private final AdministradorServiceDB db;
    
    public MenuAdministradorController(Administrador modelo, MenuAdministrador view) {
        this.administrador = modelo;
        this.menuAdministrador = view;
        db = new AdministradorServiceDB();
        
        menuAdministrador.buscarAction(new buscarOperation() );
        menuAdministrador.productosAction( new admProductOperation());
        menuAdministrador.usersAction(new admUserOperation());
        menuAdministrador.compraAction(new admCompraOperation());
        menuAdministrador.createButtonUserAction(new crearUsuarioButton());
        menuAdministrador.actualizarBtnAction(new actualizarVistaUsuarios());
        menuAdministrador.actualizarBtnProductAction(new actualizarVistaProducto());
        ventanaRegistro.btnCrearRegistro( new eventoBtnRegistroUsuario());
        ventanaRegistro.btnLimpiarRegistro(new eventoBtnLimpiarRegistro());
        
        
        cargarPedidos();
        cargarUsuarios();
        cargarProductos();
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
    
    void cargarUsuarios(){
        List<Usuario> usuarios =  db.getUsuarios();
        for(Usuario u: usuarios){
            if(!u.getEstado()){
            System.out.println(u);
            UserItem userView = new UserItem(u);
            menuAdministrador.chargerUsuarios(userView);
            }
        }
    }
    void cargarPedidos(){
        List<Pedido> pedidos =  db.getPedidos(administrador);
        for(Pedido p: pedidos){
            
            System.out.println(p);
            PedidoItem item = new PedidoItem(p);
            item.setOnMouseClicked(new OnPedidoSelected(p));
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
        }
    }
    void cargarProductos(){
        List<Articulo> articulos = db.getArticulos();
        for(Articulo a: articulos){
            if(!a.getEstado()){
            System.out.println(a);
            ArticuloItemAdm articuloView = new ArticuloItemAdm(a);
            menuAdministrador.chargerProductos(articuloView);
            }
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
             ventanaRegistro.limpiarCampos();ventanaRegistro.close();
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
    
    class actualizarVistaUsuarios implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent actionEvent) {
            menuAdministrador.clearPanelUser();
            cargarUsuarios();
        }
    }
    class actualizarVistaProducto implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent actionEvent) {
            menuAdministrador.paneVerticalListProduct.getChildren().clear();
            cargarProductos();
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
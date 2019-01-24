package models.entities;

public enum  Rol {
    ADMIN, COMPRADOR, VENDEDOR;
    
    public static Rol parseRol(String rol){
        switch(rol){
            case "A":
                return ADMIN;
            case "C":
                return COMPRADOR;
            case "V":
                return VENDEDOR;
        }
        return COMPRADOR;
    }
}

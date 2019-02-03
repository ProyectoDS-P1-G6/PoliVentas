package models.entities;

public enum  Rol {
    ADMIN, COMPRADOR, VENDEDOR;
    
    public static Rol parseRol(String rol){
        switch(rol){
            case "A":
                return ADMIN;
            case "V":
                return VENDEDOR;
        }
        return COMPRADOR;
    }
    
    public static String getRolChar(Rol rol){
        String rolChar = "C";
        switch(rol){
            case ADMIN:
                rolChar = "A";
                break;
            case VENDEDOR:
                rolChar = "V";
                break;
        }
        
        return rolChar;
    }
}

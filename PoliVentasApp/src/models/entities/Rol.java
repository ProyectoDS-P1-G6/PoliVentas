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
    public static String inParseRol(Rol rol){
        switch(rol){
            case ADMIN:
                return "A";
            case COMPRADOR:
                return "C";
            case VENDEDOR:
                return "V";
        }
        return "C";
    }
}

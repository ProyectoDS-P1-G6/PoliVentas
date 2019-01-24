package models;

public enum Estado {
    ENTREGADO, ENVIADO,PENDIENTE,ANULADO;
    
    public static Estado parseEstado(String estadoString){
        switch(estadoString){
            case "E":
                return Estado.ENTREGADO;
 
            case "P":
                return Estado.PENDIENTE;
               
            case "A":
                return Estado.ANULADO;
                
        }
        return Estado.ENVIADO;
    }
}

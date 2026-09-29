package co.sena.autocloud.modelo;
public class Vehiculo  {

    private String idPlaca;
    private String tipoVehiculo;


public Vehiculo() {
}

public Vehiculo (String idPlaca, String tipoVehiculo) {
    this.idPlaca = idPlaca;
    this.tipoVehiculo = tipoVehiculo;
}
public String getIdPlaca(){
return idPlaca;
}
public  void setIdPlaca(String idPlaca ){
this.idPlaca = idPlaca;
}
public String getTipoVehiculo(){
 return tipoVehiculo;   
}
public void  setTipoVehiculo(String tipoVehiculo){
this.tipoVehiculo = tipoVehiculo;
}

public String obtenerDatos() {
    return "Placa:"+ idPlaca +"|Tipo "+ tipoVehiculo;
}
}
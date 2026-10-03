package nomina.app;
import java.time.LocalDate;
import nomina.modelo.*;
import nomina.servicio.Nomina;
public class Main {
 public static void main(String[] args){
  EmpleadoAdministrativo ana=new EmpleadoAdministrativo(1,"Ana","12345",3000000,LocalDate.of(2024,1,10),TipoContrato.INDEFINIDO,null,"Analista",500000);
  EmpleadoOperativo juan=new EmpleadoOperativo(2,"Juan","98765",2000000,LocalDate.of(2025,2,15),TipoContrato.FIJO,LocalDate.of(2026,10,31),10,15000);
  ana.setNombre("Ana Gómez");
  Nomina nomina=new Nomina(1,LocalDate.of(2026,10,30),"Octubre 2026");
  nomina.agregarEmpleado(ana);nomina.agregarEmpleado(juan);
  System.out.println(nomina.generarInforme());
  Liquidacion liquidacion=juan.liquidarPersonal(LocalDate.of(2026,10,31),200000,300000,100000,50000);
  System.out.println("Liquidación de "+juan.getNombre()+" al "+liquidacion.getFechaFinalizacion()+": "+liquidacion.getTotal());
 }
}

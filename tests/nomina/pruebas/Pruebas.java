package nomina.pruebas;
import java.time.LocalDate;
import nomina.modelo.*;
import nomina.servicio.Nomina;
public class Pruebas {
 static void verificar(boolean v){if(!v)throw new AssertionError("Prueba fallida");}
 static void rechaza(Runnable r){try{r.run();}catch(IllegalArgumentException e){return;}throw new AssertionError("Debió rechazar");}
 public static void main(String[] args){
  LocalDate inicio=LocalDate.of(2026,1,1),fin=LocalDate.of(2026,10,31);
  Empleado a=new EmpleadoAdministrativo(1,"Ana","1",3000000,inicio,TipoContrato.INDEFINIDO,null,"Analista",500000);
  Empleado b=new EmpleadoOperativo(2,"Juan","2",2000000,inicio,TipoContrato.FIJO,fin,10,15000);
  Nomina n=new Nomina(1,fin,"Octubre");n.agregarEmpleado(a);n.agregarEmpleado(b);
  verificar(a.calcularSalario()==3500000);verificar(b.calcularSalario()==2150000);verificar(n.calcularNomina()==5650000);
  verificar(n.generarInforme().contains("Ana"));rechaza(()->n.agregarEmpleado(a));rechaza(()->a.setSalarioBase(-1));rechaza(()->a.setSalarioBase(Double.NaN));rechaza(()->a.setFechaFinalizacion(inicio.minusDays(1)));
  Liquidacion l=b.liquidarPersonal(fin,200000,300000,100000,50000);verificar(l.getTotal()==550000);
  rechaza(()->b.liquidarPersonal(fin,0,0,0,1));rechaza(()->b.liquidarPersonal(fin.plusDays(1),0,0,0,0));
  n.setFechaPago(fin.plusDays(1));verificar(n.calcularNomina()==3500000);
  try{n.getEmpleados().clear();throw new AssertionError();}catch(UnsupportedOperationException esperado){}
  verificar(a.getNombre().equals("Ana"));a.setNombre("Ana Gómez");verificar(a.getNombre().equals("Ana Gómez"));
  System.out.println("Todas las pruebas pasaron");
 }
}

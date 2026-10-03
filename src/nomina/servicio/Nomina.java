package nomina.servicio;
import nomina.modelo.Empleado;
import nomina.util.Validacion;
import java.time.LocalDate;
import java.util.*;
public class Nomina {
 private int id;
 private LocalDate fechaPago;
 private String periodo;
 private final List<Empleado> empleados=new ArrayList<>();
 public Nomina(int id, LocalDate fechaPago, String periodo){setId(id);setFechaPago(fechaPago);setPeriodo(periodo);}
 public int getId(){return id;}
 public void setId(int v){if(v<=0)throw new IllegalArgumentException("ID inválido");id=v;}
 public LocalDate getFechaPago(){return fechaPago;}
 public void setFechaPago(LocalDate v){fechaPago=Objects.requireNonNull(v);}
 public String getPeriodo(){return periodo;}
 public void setPeriodo(String v){periodo=Validacion.texto(v);}
 public List<Empleado> getEmpleados(){return Collections.unmodifiableList(empleados);}
 public void agregarEmpleado(Empleado e){
  Objects.requireNonNull(e);
  for(Empleado actual:empleados) if(actual.getId()==e.getId() || actual.getDocumento().equals(e.getDocumento())) throw new IllegalArgumentException("Empleado duplicado");
  empleados.add(e);
 }
 public double calcularNomina(){
  double total=0;
  for(Empleado e:getEmpleados())if(e.estaActivo(getFechaPago()))total+=e.calcularSalario();
  return total;
 }
 public String generarComprobante(Empleado e){
  if(!empleados.contains(e) || !e.estaActivo(getFechaPago()))throw new IllegalArgumentException("Empleado fuera de esta nómina");
  return String.format(Locale.US,"Empleado: %s | Documento: %s | Contrato: %s | Periodo: %s | Pago: %s | Devengado: %,.2f",e.getNombre(),e.getDocumento(),e.getTipoContrato(),getPeriodo(),getFechaPago(),e.calcularSalario());
 }
 /** Consulta información mediante getters y suma salarios con polimorfismo. */
 public String generarInforme(){
  StringBuilder informe=new StringBuilder("NÓMINA "+getId()+" - "+getPeriodo()+"\n");
  for(Empleado e:getEmpleados())if(e.estaActivo(getFechaPago()))informe.append(generarComprobante(e)).append("\n");
  return informe.append(String.format(Locale.US,"TOTAL: %,.2f%n",calcularNomina())).toString();
 }
}

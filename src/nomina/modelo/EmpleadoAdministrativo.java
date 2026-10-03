package nomina.modelo;
import java.time.LocalDate;
import nomina.util.Validacion;
public class EmpleadoAdministrativo extends Empleado {
 private String cargo;
 private double bonificacion;
 public EmpleadoAdministrativo(int id, String nombre, String documento, double salarioBase, LocalDate fechaIngreso, TipoContrato tipoContrato, LocalDate fechaFinalizacion, String cargo, double bonificacion) {
 super(id,nombre,documento,salarioBase,fechaIngreso,tipoContrato,fechaFinalizacion);
 setCargo(cargo);
 setBonificacion(bonificacion);
 }
 public String getCargo() { return cargo; }
 public void setCargo(String v) { this.cargo=Validacion.texto(v); }
 public double getBonificacion() { return bonificacion; }
 public void setBonificacion(double v) { this.bonificacion=Validacion.dinero(v); }
 @Override public double calcularSalario() { return getSalarioBase() + getBonificacion(); }
}

package nomina.modelo;
import java.time.LocalDate;
import nomina.util.Validacion;
public class EmpleadoOperativo extends Empleado {
 private int horasExtra;
 private double valorHoraExtra;
 public EmpleadoOperativo(int id, String nombre, String documento, double salarioBase, LocalDate fechaIngreso, TipoContrato tipoContrato, LocalDate fechaFinalizacion, int horasExtra, double valorHoraExtra) {
 super(id,nombre,documento,salarioBase,fechaIngreso,tipoContrato,fechaFinalizacion);
 setHorasExtra(horasExtra);
 setValorHoraExtra(valorHoraExtra);
 }
 public int getHorasExtra() { return horasExtra; }
 public void setHorasExtra(int v) { if(v<0) throw new IllegalArgumentException("Horas negativas"); this.horasExtra=v; }
 public double getValorHoraExtra() { return valorHoraExtra; }
 public void setValorHoraExtra(double v) { this.valorHoraExtra=Validacion.dinero(v); }
 @Override public double calcularSalario() { return getSalarioBase() + getHorasExtra() * getValorHoraExtra(); }
}

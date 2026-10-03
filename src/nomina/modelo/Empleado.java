package nomina.modelo;
import java.time.LocalDate;
import java.util.Objects;
import nomina.util.Validacion;
/** Datos comunes. Las clases hijas acceden a atributos privados mediante getters. */
public abstract class Empleado {
 private int id;
 private String nombre;
 private String documento;
 private double salarioBase;
 private LocalDate fechaIngreso;
 private TipoContrato tipoContrato;
 private LocalDate fechaFinalizacion;
 public Empleado(int id, String nombre, String documento, double salarioBase, LocalDate fechaIngreso, TipoContrato tipoContrato, LocalDate fechaFinalizacion) {
  setId(id);
  setNombre(nombre);
  setDocumento(documento);
  setSalarioBase(salarioBase);
  setFechaIngreso(fechaIngreso);
  setTipoContrato(tipoContrato);
  setFechaFinalizacion(fechaFinalizacion);
 }
 public int getId() { return id; }
 public void setId(int v) { if(v <= 0) throw new IllegalArgumentException("ID debe ser positivo"); this.id=v; }
 public String getNombre() { return nombre; }
 public void setNombre(String v) { this.nombre=Validacion.texto(v); }
 public String getDocumento() { return documento; }
 public void setDocumento(String v) { this.documento=Validacion.texto(v); }
 public double getSalarioBase() { return salarioBase; }
 public void setSalarioBase(double v) { this.salarioBase=Validacion.dinero(v); }
 public LocalDate getFechaIngreso() { return fechaIngreso; }
 public void setFechaIngreso(LocalDate v) { Objects.requireNonNull(v); if(fechaFinalizacion != null && v.isAfter(fechaFinalizacion)) throw new IllegalArgumentException("Ingreso posterior a finalización"); this.fechaIngreso=v; }
 public TipoContrato getTipoContrato() { return tipoContrato; }
 public void setTipoContrato(TipoContrato v) { this.tipoContrato=Objects.requireNonNull(v); }
 public LocalDate getFechaFinalizacion() { return fechaFinalizacion; }
 public void setFechaFinalizacion(LocalDate v) { if(v != null && v.isBefore(fechaIngreso)) throw new IllegalArgumentException("Finalización anterior a ingreso"); this.fechaFinalizacion=v; }
 public boolean estaActivo(LocalDate fecha) {
  Objects.requireNonNull(fecha);
  return !fecha.isBefore(fechaIngreso) && (fechaFinalizacion == null || !fecha.isAfter(fechaFinalizacion));
 }
 public abstract double calcularSalario();
 /** Registra terminación y devuelve la liquidación de saldos previamente calculados. */
 public Liquidacion liquidarPersonal(LocalDate fin, double salarioPendiente, double prestacionesPendientes, double vacacionesPendientes, double deducciones) {
  Objects.requireNonNull(fin);
  if(fechaFinalizacion != null && !fin.equals(fechaFinalizacion)) throw new IllegalArgumentException("La fecha debe coincidir con la finalización registrada");
  Liquidacion resultado = new Liquidacion(id, fin, salarioPendiente, prestacionesPendientes, vacacionesPendientes, deducciones);
  setFechaFinalizacion(fin);
  return resultado;
 }
}

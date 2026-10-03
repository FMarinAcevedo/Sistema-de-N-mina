package nomina.modelo;
import java.time.LocalDate;
import java.util.Objects;
import nomina.util.Validacion;
/** Resultado inmutable: no permite alterar saldos después de emitirlo. */
public final class Liquidacion {
 private final int empleadoId;
 private final LocalDate fechaFinalizacion;
 private final double salarioPendiente, prestacionesPendientes, vacacionesPendientes, deducciones;
 public Liquidacion(int id, LocalDate fecha, double salario, double prestaciones, double vacaciones, double descuentos) {
  if(id<=0) throw new IllegalArgumentException("ID inválido");
  empleadoId=id; fechaFinalizacion=Objects.requireNonNull(fecha);
  salarioPendiente=Validacion.dinero(salario); prestacionesPendientes=Validacion.dinero(prestaciones);
  vacacionesPendientes=Validacion.dinero(vacaciones); deducciones=Validacion.dinero(descuentos);
  if(descuentos>salario+prestaciones+vacaciones) throw new IllegalArgumentException("Deducciones superiores a saldos");
 }
 public int getEmpleadoId(){return empleadoId;}
 public LocalDate getFechaFinalizacion(){return fechaFinalizacion;}
 public double getSalarioPendiente(){return salarioPendiente;}
 public double getPrestacionesPendientes(){return prestacionesPendientes;}
 public double getVacacionesPendientes(){return vacacionesPendientes;}
 public double getDeducciones(){return deducciones;}
 public double getTotal(){return salarioPendiente+prestacionesPendientes+vacacionesPendientes-deducciones;}
}

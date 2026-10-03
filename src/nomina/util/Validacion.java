package nomina.util;
public final class Validacion {
 private Validacion() {}
 public static double dinero(double v) {
  if (!Double.isFinite(v) || v < 0) throw new IllegalArgumentException("Valor monetario inválido");
  return v;
 }
 public static String texto(String v) {
  if(v == null || v.trim().isEmpty()) throw new IllegalArgumentException("Texto obligatorio");
  return v.trim();
 }
}

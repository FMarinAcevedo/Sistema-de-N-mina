# Sistema de nómina — proyecto académico Java

Requiere JDK 17 o posterior; no necesita bibliotecas externas. Los requisitos implementados son los compartidos en la conversación; no se recibió un documento adicional de historias de usuario.

## Ejecutar
Extrae la carpeta completa. En Windows ejecuta `ejecutar.bat`; en Linux/macOS usa `bash ejecutar.sh`. En un IDE abre la carpeta, marca `src` como directorio de fuentes y ejecuta `nomina.app.Main`. Para pruebas, después de compilar: `java -cp out nomina.pruebas.Pruebas`.

El ejemplo muestra nómina de Ana ($3.500.000) y Juan ($2.150.000), total $5.650.000; después liquida a Juan por $550.000. El separador monetario de consola sigue Locale.US.

## Paquetes
- `nomina.modelo`: Empleado abstracto, sus dos subclases, TipoContrato y Liquidacion.
- `nomina.servicio`: Nomina, lista de empleados, cálculo, comprobantes e informe.
- `nomina.util`: validaciones compartidas.
- `nomina.app`: ejemplo ejecutable Main.
- `nomina.pruebas`: verificaciones sin dependencias.

## Alcance de los cálculos
Este es un modelo académico de devengados. Administrativo = salario base + bonificación; operativo = salario base + horas extra × valor pactado. No calcula automáticamente descuentos legales ni prorratea meses. Una nómina incluye empleados activos en su fecha de pago, incluyendo el día de finalización. Para terminar contratos indefinidos u obra labor, llama a liquidarPersonal con la fecha real. En fijo, registra inicialmente la fecha final; si hay prórroga, actualízala mediante el setter antes de liquidar.

La liquidación suma salario pendiente, prestaciones pendientes y vacaciones pendientes, menos deducciones. Estos importes se suministran ya calculados y no deben incluir cantidades previamente pagadas. No constituye un motor de liquidación laboral de Colombia: no aplica tasas legales, indemnizaciones, cesantías o intereses automáticamente. El requisito no proporcionó reglas para esas partidas. No hay pagos bancarios, base de datos ni persistencia: los datos viven en memoria. La llamada devuelve un resultado y registra el fin del contrato; no registra un desembolso. Repetirla para la misma fecha permite recalcular, no pagar dos veces.

## Conceptos
Atributos privados y validación en setters demuestran encapsulamiento. Constructores inicializan objetos; `super` reutiliza el constructor padre. Las clases hijas sobrescriben calcularSalario; Nomina llama el método por referencia Empleado (polimorfismo). Cada clase reside en un archivo dentro de su paquete (modularidad). Liquidacion es inmutable y solo expone getters. La lista de empleados es de solo lectura; se agregan por agregarEmpleado.

Consulta `docs/HISTORIAS_USUARIO.md` para trazabilidad y `docs/diagrama.mmd` para UML actualizado. Nomina es un cálculo vivo: modificar un empleado cambia sus próximos informes; este ejemplo no conserva cierres históricos.

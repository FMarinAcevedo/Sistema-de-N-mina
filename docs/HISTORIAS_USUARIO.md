# Historias derivadas de los requisitos compartidos

| Historia | Criterio de aceptación | Implementación |
|---|---|---|
| HU01 Como usuario quiero registrar datos comunes de empleados | ID positivo, nombre, documento, salario y fecha de ingreso | Constructor Empleado y setters |
| HU02 Quiero registrar administrativos | Cargo y bonificación; devengado = base + bono | EmpleadoAdministrativo |
| HU03 Quiero registrar operativos | Horas extra y valor no negativos; base + horas × valor | EmpleadoOperativo |
| HU04 Quiero consultar y actualizar datos controladamente | Getters y setters; salario negativo rechazado | Clases del modelo |
| HU05 Quiero identificar contratos | FIJO, OBRA_LABOR o INDEFINIDO; fecha final opcional | TipoContrato y Empleado |
| HU06 Quiero liquidar al terminar contrato | Fecha no anterior al ingreso; sumar saldos y restar descuentos | liquidarPersonal y Liquidacion |
| HU07 Quiero calcular nómina | Suma polimórfica de empleados activos, sin registros duplicados | calcularNomina y agregarEmpleado |
| HU08 Quiero comprobantes e informe total | Identificación, contrato, periodo, fecha y valores consultados con getters | generarComprobante y generarInforme |
| HU09 Quiero código modular con herencia | Archivos por paquete, padre abstracto y subclases | src/nomina |

Las pruebas cubren salarios, total, informe, setters, duplicados, fechas, valores no finitos, liquidación y exclusión tras la terminación. No se aportaron historias adicionales fuera de estas instrucciones.

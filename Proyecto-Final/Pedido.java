public class Pedido implements Envio {
    private Producto producto;

    public Pedido(Producto producto) {
        this.producto = producto;
    }
    @Override
    public void procesarEnvio() {
        try {
            System.out.println("Procesando envío para el producto: " + producto.getNombre());
            // Lógica de envío aquí
            if (producto.getPrecio() < 0) {
                throw new Exception("Precio inválido en el producto");
            }
        } catch (Exception e) {
            // Manejo de excepciones generales
            System.out.println("Error al procesar el envío: " + e.getMessage());
        }
    }
}

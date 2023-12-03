public class ProductoElectronico extends Producto {
    private String tipo;

    public ProductoElectronico(int id, String nombre, int stock, double precio, String tipo) {
        super(id, nombre, stock, precio);
        this.tipo = tipo;
    }

    // Getter específico para tipo
    public String getTipo() {
        return tipo;
    }

    // Setter específico para tipo
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

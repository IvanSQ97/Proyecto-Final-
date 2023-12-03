import java.util.ArrayList;

public class Tienda {
    private ArrayList<Producto> inventario;

    public Tienda() {
        this.inventario = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        inventario.add(producto);
    }

    public void venderProducto(int id) {
        // Lógica para vender un producto según su ID
        for (Producto producto : inventario) {
            if (producto.getId() == id) {
                if (producto.getStock() > 0) {
                    producto.setStock(producto.getStock() - 1);
                    System.out.println("Producto vendido: " + producto.getNombre());
                } else {
                    System.out.println("El producto está agotado.");
                }
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }

    public void mostrarInventario() {
        System.out.println("Inventario de la tienda:");
        for (Producto producto : inventario) {
            System.out.println("ID: " + producto.getId() +
                    ", Nombre: " + producto.getNombre() +
                    ", Stock: " + producto.getStock() +
                    ", Precio: " + producto.getPrecio());
        }
    }
    public ArrayList<Producto> getInventario() {
        return inventario;
    }
    
    public void setInventario(ArrayList<Producto> inventario) {
        this.inventario = inventario;
    }
}

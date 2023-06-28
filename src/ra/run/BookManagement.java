package ra.run;

import ra.model.Catalog;
import ra.model.Product;
import ra.service.CatalogService;
import ra.service.ProductService;

import java.util.List;
import java.util.Scanner;

public class BookManagement {
    private static CatalogService catalogService;
    private static ProductService productService;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        catalogService = new CatalogService();
        productService = new ProductService();

        int check;

        do {
            System.out.println("**************************BASIC-MENU**************************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            check = scanner.nextInt();
            scanner.nextLine();

            switch (check) {
                case 1:
                    catalogManagementMenu(scanner);
                    break;
                case 2:
                    productManagementMenu(scanner);
                    break;
                case 3:
                    System.out.println("Chương trình kết thúc.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (check != 3);
    }

    private static void catalogManagementMenu(Scanner scanner) {
        int check;

        do {
            System.out.println("********************CATALOG-MANAGEMENT********************");
            System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục");
            System.out.println("2. Hiển thị thông tin tất cả các danh mục");
            System.out.println("3. Sửa tên danh mục theo mã danh mục");
            System.out.println("4. Xóa danh mục theo mã danh mục (lưu ý không xóa khi có sản phẩm)");
            System.out.println("5. Quay lại");
            System.out.print("Nhập lựa chọn của bạn: ");
            check = scanner.nextInt();
            scanner.nextLine();

            switch (check) {
                case 1:
                    saveCatalog(scanner);
                    break;
                case 2:
                    displayAllCatalogs();
                    break;
                case 3:
                    updateCatalogName(scanner);
                    break;
                case 4:
                    deleteCatalog(scanner);
                    break;
                case 5:
                    System.out.println("Quay lại BASIC-MENU.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (check != 5);
    }

    private static void productManagementMenu(Scanner scanner) {
        int check;

        do {
            System.out.println("********************PRODUCT-MANAGEMENT********************");
            System.out.println("1. Nhập số sản phẩm và nhập thông tin sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp sản phẩm theo giá giảm dần");
            System.out.println("4. Xóa sản phẩm theo mã");
            System.out.println("5. Tìm kiếm sách theo tên sách");
            System.out.println("6. Thay đổi thông tin của sách theo mã sách");
            System.out.println("7. Quay lại");
            System.out.print("Nhập lựa chọn của bạn: ");
            check = scanner.nextInt();
            scanner.nextLine();

            switch (check) {
                case 1:
                    addProducts(scanner);
                    break;
                case 2:
                    displayAllProducts();
                    break;
                case 3:
                    sortProductsByPriceDescending();
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    searchProductByName(scanner);
                    break;
                case 6:
                    updateProductInformation(scanner);
                    break;
                case 7:
                    System.out.println("Quay lại BASIC-MENU.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (check != 7);
    }

    private static void saveCatalog(Scanner scanner) {
        System.out.print("Nhập số danh mục cần thêm mới: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; i++) {
            System.out.println("Nhập thông tin cho danh mục thứ " + (i + 1));
            System.out.print("Nhập mã danh mục: ");
            int catalogId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nhập tên danh mục: ");
            String catalogName = scanner.nextLine();

            Catalog catalog = new Catalog(catalogId, catalogName);
            catalogService.save(catalog);
            System.out.println("Danh mục đã được thêm mới.");
        }
    }

    private static void displayAllCatalogs() {
        List<Catalog> catalogs = catalogService.getAll();

        if (catalogs.isEmpty()) {
            System.out.println("Danh sách danh mục rỗng.");
        } else {
            System.out.println("Danh sách các danh mục:");
            for (Catalog catalog : catalogs) {
                System.out.println(catalog);
            }
        }
    }

    private static void updateCatalogName(Scanner scanner) {
        System.out.print("Nhập mã danh mục cần sửa tên: ");
        int catalogId = scanner.nextInt();
        scanner.nextLine();

        Catalog catalog = catalogService.findById(catalogId);

        if (catalog == null) {
            System.out.println("Không tìm thấy danh mục có mã " + catalogId);
        } else {
            System.out.print("Nhập tên danh mục mới: ");
            String newCatalogName = scanner.nextLine();
            catalog.setCatalogName(newCatalogName);
            System.out.println("Tên danh mục đã được cập nhật.");
        }
    }

    private static void deleteCatalog(Scanner scanner) {
        System.out.print("Nhập mã danh mục cần xóa: ");
        int catalogId = scanner.nextInt();
        scanner.nextLine();

        Catalog catalog = catalogService.findById(catalogId);

        if (catalog == null) {
            System.out.println("Không tìm thấy danh mục có mã " + catalogId);
        } else {
            catalogService.delete(catalogId);
            System.out.println("Danh mục đã được xóa.");
        }
    }

    private static void addProducts(Scanner scanner) {
        System.out.print("Nhập số sản phẩm cần thêm mới: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; i++) {
            System.out.println("Nhập thông tin cho sản phẩm thứ " + (i + 1));
            System.out.print("Nhập mã sản phẩm: ");
            String productId = scanner.nextLine();
            System.out.print("Nhập tên sản phẩm: ");
            String productName = scanner.nextLine();
            System.out.print("Nhập giá sản phẩm: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            Product product = new Product(productId, productName, price);
            productService.save(product);
            System.out.println("Sản phẩm đã được thêm mới.");
        }
    }

    private static void displayAllProducts() {
        List<Product> products = productService.getAll();

        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm rỗng.");
        } else {
            System.out.println("Danh sách các sản phẩm:");
            for (Product product : products) {
                System.out.println("Product ID: " + product.getProductId());
                System.out.println("Product Name: " + product.getProductName());
                System.out.println("Product Price: " + product.getProductPrice());
                System.out.println("------------------------");
            }
        }
    }

    private static void sortProductsByPriceDescending() {
        List<Product> products = productService.getAll();

        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm rỗng.");
        } else {
            productService.sortByPriceDescending();
            System.out.println("Danh sách sản phẩm sau khi sắp xếp theo giá giảm dần:");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    private static void deleteProduct(Scanner scanner) {
        System.out.print("Nhập mã sản phẩm cần xóa: ");
        String productId = scanner.nextLine();

        Product product = productService.findById(productId);

        if (product == null) {
            System.out.println("Không tìm thấy sản phẩm có mã " + productId);
        } else {
            productService.delete(productId);
            System.out.println("Sản phẩm đã được xóa.");
        }
    }

    private static void searchProductByName(Scanner scanner) {
        System.out.print("Nhập tên sách cần tìm kiếm: ");
        String productName = scanner.nextLine();

        List<Product> products = productService.searchByName(productName);

        if (products.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào có tên " + productName);
        } else {
            System.out.println("Kết quả tìm kiếm:");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    private static void updateProductInformation(Scanner scanner) {
        System.out.print("Nhập mã sản phẩm cần sửa thông tin: ");
        String productId = scanner.nextLine();

        Product product = productService.findById(productId);

        if (product == null) {
            System.out.println("Không tìm thấy sản phẩm có mã " + productId);
        } else {
            System.out.print("Nhập tên mới cho sản phẩm: ");
            String newProductName = scanner.nextLine();
            System.out.print("Nhập giá mới cho sản phẩm: ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine();

            product.setProductName(newProductName);
            product.setProductPrice(newPrice);
            System.out.println("Thông tin sản phẩm đã được cập nhật.");
        }
    }
}

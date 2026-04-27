package com.proyecto;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import com.proyecto.model.Pokemon;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * La clase <code>SceneManager</code> es responsable de gestionar las escenas de
 * la aplicación JavaFX.
 * Permite cargar, almacenar, cambiar y eliminar escenas. Además, gestiona la
 * asignación de hojas de estilo (CSS)
 * para cada escena y se asegura de que la escena actual se cargue en la ventana
 * principal (Stage).
 */
public class SceneManager {
    // Instancia única (singleton) de SceneManager
    private static SceneManager instance;

    private Stage stage; // La ventana principal de la aplicación
    private String base;  // La ruta base de la carpeta de vistas
    private URL styles; // Ruta a la hoja de estilo CSS que se aplica a las escenas
    private HashMap<String, URL> scenes; // Mapa para almacenar las escenas según su identificador

    private double screenWidthRatio;
    private double screenHeightRatio;

    /**
     * Constructor privado de <code>SceneManager</code>.
     * Inicializa el mapa de escenas vacío.
     */
    private SceneManager() {
        scenes = new HashMap<>();
    }

    /**
     * Método estático para obtener la instancia única de <code>SceneManager</code>
     * (patrón Singleton).
     * 
     * @return La instancia única de <code>SceneManager</code>.
     */
    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    /**
     * Inicializa el <code>SceneManager</code> con el <code>Stage</code> principal y
     * la ruta de la hoja de estilo.
     * 
     * @param stage  la ventana principal de la aplicación donde se mostrarán las escenas.
     * @param base   la ruta base de la localización de las vistas de la aplicación
     * @param screenWidthRatio    el porcentaje de la pantalla en anchura
     * @param screenHeightRatio   el porcentaje de la pantalla en altura
     * @param styles el nombre de la hoja de estilo CSS a aplicar a las escenas.
     */
    @SuppressWarnings("exports")
    public void init(Stage stage, String base, double screenWidthRatio, double screenHeightRatio, String styles) {
        this.stage = stage;
        styles = styles.endsWith(".css")?styles:styles+".css";
        this.styles = App.class.getResource("styles/" + styles); // Ruta al archivo CSS
        this.base = base.endsWith("/")||base.isBlank()?base:base+"/";
        this.screenWidthRatio = screenWidthRatio;
        this.screenHeightRatio = screenHeightRatio;
    }

    /**
     * Inicializa el <code>SceneManager</code> con el <code>Stage</code> principal y
     * la ruta de la hoja de estilo.
     * 
     * @param stage la ventana principal de la aplicación donde se mostrarán las
     *              escenas.
     */
    @SuppressWarnings("exports")
    public void init(Stage stage) {
        this.stage = stage;
        this.base = "";
    }

    /**
     * Añade una escena nueva al gestor
     * 
     * @param sceneID el identificador único de la escena.
     * @param fxml    el nombre del archivo FXML que define la vista de la escena.
     */
    public void addScene(String sceneID, String fxml) throws IOException {
        fxml = fxml.endsWith(".fxml")?fxml:fxml+".fxml";
        // Carga el archivo FXML
        URL url = App.class.getResource( base + fxml);

        if (url!=null)
            scenes.put(sceneID, url); // Almacena la escena en el mapa con el identificador correspondiente
        else
            throw new IOException("No se encuentra la ruta de la vista " + sceneID);
    }

    /**
     * Elimina una escena previamente almacenada usando su identificador.
     * 
     * @param sceneID el identificador único de la escena que se desea eliminar.
     */
    public void removeScene(String sceneID) {
        scenes.remove(sceneID); // Elimina la escena del mapa
    }

    /**
     * Carga y muestra una escena previamente almacenada en el
     * <code>SceneManager</code>.
     * 
     * @param sceneID el identificador único de la escena que se desea cargar.
     * @param elegido 
     */
    public void loadScene(String sceneID) {
        if (scenes.containsKey(sceneID)) {
            // Obtener la pantalla principal
            Screen screen = Screen.getPrimary();

            // Obtener el tamaño de la pantalla
            double screenWidth = screen.getBounds().getWidth();
            double screenHeight = screen.getBounds().getHeight();
            FXMLLoader fxmlLoader = new FXMLLoader(scenes.get(sceneID));
            Parent root;
            try {
                root = fxmlLoader.load();
                Scene scene = new Scene(root, screenWidth * screenWidthRatio, screenHeight * screenHeightRatio); // Crea la escena con el tamaño especificado
                if (styles != null)
                    scene.getStylesheets().add(styles.toExternalForm());        // Añade la hoja de estilo
                stage.setScene(scene);                                          // Establece la escena en la ventana principal
                stage.show();                                                   // Muestra la ventana con la nueva escena
            } catch (IOException e) {
                System.err.println("Error al cargar la vista: " + sceneID);
                e.printStackTrace();
            }

        } else {
            System.err.println("La escena seleccionada no existe");
        }
    }

    @SuppressWarnings("unchecked")
    public void loadScene(String sceneID, Object data) {
        if (scenes.containsKey(sceneID)) {
            Screen screen = Screen.getPrimary();
            double screenWidth = screen.getBounds().getWidth();
            double screenHeight = screen.getBounds().getHeight();
            FXMLLoader fxmlLoader = new FXMLLoader(scenes.get(sceneID));
            Parent root;
            try {
                root = fxmlLoader.load();
                Object controller = fxmlLoader.getController();
                if (controller instanceof DataReceiver) {
                    ((DataReceiver<Object>) controller).receiveData(data);
                }
                Scene scene = new Scene(root, screenWidth * screenWidthRatio, screenHeight * screenHeightRatio);
                if (styles != null) scene.getStylesheets().add(styles.toExternalForm());
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println("Error al cargar la vista: " + sceneID);
                e.printStackTrace();
            }
        } else {
            System.err.println("La escena seleccionada no existe");
        }
    }
}
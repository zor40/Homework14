import java.io.IOException;

public class BestResultNotFound extends IOException {
    public BestResultNotFound(String massage) {
        super("Объекты, в которых встречается \"" + massage + "\" не найдены");
    }
}
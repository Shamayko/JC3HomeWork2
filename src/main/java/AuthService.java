public interface AuthService {

    //Создаем интерфейс, который контрактует нам методы старта, стопа и получения логина.
    void start();

    void stop();

    String getNickByLoginAndPass(String login, String pass);
}


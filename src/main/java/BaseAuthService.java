import java.util.ArrayList;
import java.util.List;


public class BaseAuthService implements AuthService {
    //Создаем класс для хранения логинов, паролей и ников
    private static class Entry {
        private final String nick;
        private final String login;
        private final String pass;

        //Создаем конструктор класса
        public Entry(String login, String pass, String nick) {
            this.nick = nick;
            this.login = login;
            this.pass = pass;
        }
    }

    //Объявляем переменную типа Лист для хранений авторизационных данных
    private final List<Entry> entries;

    //Создаем объект класса BaseAuthService и наполняем его авторизационными данными.
    public BaseAuthService() {
        entries = new ArrayList<>();
        entries.add(new Entry("l1", "p1", "Ctulhu"));
        entries.add(new Entry("l2", "p2", "PinkPanter"));
        entries.add(new Entry("l3", "p3", "Homer"));
    }

    //Переопределяем методы старт, стоп и получения Ника по логину и паролю.
    @Override
    public void start() {
        System.out.println(this.getClass().getName() + " server started");
    }

    @Override
    public void stop() {
        System.out.println(this.getClass().getName() + " server stopped");
    }

    @Override
    public String getNickByLoginAndPass(String login, String pass) {
        //здесь творится темная магия, которая вызывает у объекта entries yекий поток, в котором
        //проверяет наличие частной entry в масиве, содержащей запись аналогичную по логину и паролю
        // поданым, если такая есть, то берет у нее данные из поля ник и возвращает его методу.
        return entries.stream()
                .filter(entry -> entry.login.equals(login) && entry.pass.equals(pass))
                .map(entry -> entry.nick)
                .findFirst().orElse(null);
    }
}
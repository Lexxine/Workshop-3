# Projekt: UsersCRUD

## Opis Projektu

Projekt "UsersCRUD" to aplikacja webowa umożliwiająca zarządzanie użytkownikami. Aplikacja wykorzystuje serwlety, wzorzec MVC oraz bazę danych MySQL do operacji CRUD (Create, Read, Update, Delete) na użytkownikach. Projekt ma na celu praktyczne zastosowanie technologii takich jak serwlety, wzorzec MVC, JDBC oraz Tomcat.

## Funkcjonalności Aplikacji

### Ekran Wyświetlający Listę Wszystkich Użytkowników
- **Adres URL:** `/user/list`
- **Opis:** Wyświetla listę wszystkich użytkowników. Użytkownicy mają możliwość:
  - Usunięcia wybranego użytkownika.
  - Edytowania wybranego użytkownika.
  - Przeglądania szczegółów wybranego użytkownika.
- **Link do formularza dodawania użytkownika:** Umożliwia przejście do formularza dodawania nowego użytkownika.
  ![Zdjęcie ekranu głównego](img/Screenshot1.png)
### Formularz Dodawania Użytkownika
- **Adres URL:** `/createUser`
- **Opis:** Umożliwia dodanie nowego użytkownika. Po zatwierdzeniu formularza użytkownik jest przekierowywany do listy użytkowników.

### Formularz Edycji Użytkownika
- **Adres URL:** `/user/edit{id}`
- **Opis:** Umożliwia edytowanie danych istniejącego użytkownika. Formularz wyświetla aktualne dane użytkownika (oprócz hasła, które należy wpisać ponownie). Po zapisaniu zmian użytkownik jest przekierowywany do listy użytkowników.

### Wyświetlanie Danych Użytkownika
- **Adres URL:** `/showUser{id}`
- **Opis:** Wyświetla szczegółowe informacje o użytkowniku. Po przeglądnięciu danych użytkownik jest przekierowywany do listy użytkowników.

### Usuwanie Użytkownika
- **Adres URL:** `/user/delete`
- **Opis:** Usuwa użytkownika z bazy danych. Po usunięciu użytkownika użytkownik jest przekierowywany do listy użytkowników.

## Konfiguracja Połączenia z Bazą Danych

Aplikacja korzysta z konfiguracji Tomcat oraz zasobu `DataSource` do zarządzania połączeniem z bazą danych MySQL. Plik konfiguracyjny `context.xml` jest umieszczony w katalogu `src/main/webapp/META-INF`. Zawiera on szczegóły dotyczące połączenia, takie jak adres URL bazy danych, dane logowania oraz właściwości połączenia.

## Technologie

- **Frontend:** HTML, CSS (szablon SB Admin 2)
- **Backend:** Java, Serwlety, JDBC, MySQL
- **Kontener Serwletów:** Apache Tomcat
- **Zarządzanie zależnościami:** Maven

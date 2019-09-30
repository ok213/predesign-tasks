**Написать CRUD приложение.**

_Условия:_
 - Должен быть класс User  с произвольными полями (id, name, password, login);
 - В приложении должна быть страница, на которую выводятся все юзеры
   с возможностью добавлять, удалять и изменять юзера;
- Конфигурация сервлетов через аннотации;
- Модель User через аннотации. 

_Использовать:_
 - Tomcat
 - maven
 - JSP
 - JSTL
 - сервлеты
 - JDBC.

_Дополнительно:_
- UserDao две реализации UserDaoJDBCimpl и UserDaoHibernateImpl;
- UserDaoFactory - абстрактная фабрика, которая возвращает реализацию DAO
  на основе файла property;
- DBHelper - синглтон, у него есть два метода getConnection и getConfiguration,
  которые отдают Connection для jdbc dao и Configuration для hibernatedao соотвтетственно

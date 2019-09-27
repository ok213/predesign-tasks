а) Прочитать про паттерны Синглтон и Абстрактная фабриика
б) У UserDao две реализации UserDaoJDBCimpl и UserDaoHibernateImpl
в) UserDaoFactory - абстрактная фабрика которая возвращает реализацию дао на основе файла property
г) DBHelper - синглтон, у него есть два метода getConnection и getConfiguration
   которые отдают Connection для jdbc dao и Configuration для hibernatedao соотвтетственно


   после этой таски созваниваемся и теорию спрашиваю
import psycopg2 as db
from abc import ABC, abstractmethod


class Postgres(ABC):
    def __init__(self):
        self.table = ""
        self.cursor = None
        self.connection = None
        super(Postgres, self).__init__()

    def connect(self):
        try:
            user, password, host, port, database = self.__getCredentials()
            connection = db.connect(database=database, user=user, password=password, host=host, port=port)
            cursor = connection.cursor()
            self.cursor = cursor
            self.connection = connection
            return cursor, connection
        except (Exception, db.Error) as error:
            print("Error while connecting to Postgres", error)
            return False

    def __executeAndCommit(self, query):
        self.cursor.execute(query)
        self.connection.commit()

    def __getCredentials(self):
        user = "clinic_db"
        password = "root"
        host = "localhost"
        port = "5432"
        database = "clinicdb"

        return user, password, host, port, database

    # @abstractmethod
    # def insert(self, insertParameters):
    #     query = "INSERT INTO " + self.table + " VALUES (" + insertParameters + ");"
    #     self.__executeAndCommit(query)
    #
    # @abstractmethod
    # def update(self, updateParameters, whereClause):
    #     pass
    #

    @abstractmethod
    def selectAll(self):
        query = "SELECT * FROM " + "clinicdb2."+self.table + ";"
        self.cursor.execute(query)
        print(query)

        return self.cursor.fetchall()

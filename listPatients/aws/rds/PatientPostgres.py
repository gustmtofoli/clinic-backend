from aws.rds.Postgres import Postgres


class PatientPostgres(Postgres):
    table = "patient"

    def selectAll(self):
        return super().selectAll()

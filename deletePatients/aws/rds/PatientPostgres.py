from aws.rds.Postgres import Postgres


class PatientPostgres(Postgres):
    table = "patient"

    def delete(self, id):
        super().delete(id)


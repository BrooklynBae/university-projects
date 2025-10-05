from swiplserver import PrologThread


class ListMarriagesBeforeYear:
    def __init__(self, year: str):
        self.year = int(year)

    def run(self, prolog: PrologThread):
        res = prolog.query(f"married(Husband, Wife, Year), Year < {self.year}")

        if res:
            marriages = []

            for item in res:
                husband = item['Husband']
                wife = item['Wife']
                year = item['Year']
                marriages.append(f"{husband} и {wife} ({year} год)")

            self.success(marriages)
        else:
            self.failure()

    def success(self, marriages):
        print(f"Браки, заключенные до {self.year} года:")
        for marriage in marriages:
            print(f" - {marriage}")

    def failure(self):
        print(f"Не найдено браков, заключенных до {self.year} года")
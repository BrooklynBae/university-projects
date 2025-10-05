from swiplserver import PrologThread


class CheckHasSiblings:
    def __init__(self, person: str):
        self.person = person

    def run(self, prolog: PrologThread):
        res = prolog.query(f"isSibling({self.person}, X)")

        if res:
            siblings = []

            for item in res:
                values_list = list(item.values())
                sibling_name = values_list[0]
                if sibling_name != self.person:
                    siblings.append(sibling_name)

            if siblings:
                self.success(siblings)
            else:
                self.failure()
        else:
            self.failure()

    def success(self, siblings):
        print(f"Да, у {self.person} есть родные братья/сестры:")
        for sibling in siblings:
            print(f" - {sibling}")

    def failure(self):
        print(f"Нет, у {self.person} нет родных братьев/сестер")
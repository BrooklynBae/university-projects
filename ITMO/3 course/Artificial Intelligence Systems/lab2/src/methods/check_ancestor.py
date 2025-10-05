from swiplserver import PrologThread


class CheckAncestor:
    def __init__(self, potential_ancestor: str, person: str):
        self.potential_ancestor = potential_ancestor
        self.person = person

    def run(self, prolog: PrologThread):
        res = prolog.query(f"isAncestor({self.potential_ancestor}, {self.person})")

        if res:
            self.success()
        else:
            self.failure()

    def success(self):
        print(f"Да, {self.potential_ancestor} является предком {self.person}")

    def failure(self):
        print(f"Нет, {self.potential_ancestor} не является предком {self.person}")
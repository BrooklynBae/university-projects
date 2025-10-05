import re
from swiplserver import PrologMQI, create_posix_path
from src.methods.find_parents import FindParents
from src.methods.find_children import FindChildren
from src.methods.find_siblings import FindSiblings
from src.methods.find_grandparent import FindGrandparents
from src.methods.compare_age import CompareAge
from src.methods.find_same_age import FindSameAge
from src.methods.check_marriage_before_year import CheckMarriageBeforeYear
from src.methods.check_siblings import CheckSiblings
from src.methods.check_grandparent import CheckGrandparent
from src.methods.check_has_siblings import CheckHasSiblings
from src.methods.check_mother_in_law import CheckMotherInLaw
from src.methods.list_marriage_before_year import ListMarriagesBeforeYear
from src.methods.check_ancestor import CheckAncestor
from src.methods.check_were_married import CheckWereMarried
from src.methods.check_older import CheckOlder
from src.methods.list_people_without_siblings_younger_than import ListPeopleWithoutSiblingsYoungerThan

KNOWLEDGE_BASE_PATH = "./src/prolog/family.pl"

requests = [
    "Кто родители galina?",
    "Кто дети alexey?",
    "Кто братья и сестры valentina?",
    "Кто бабушки/дедушки vlad?",
    "Кто старше: kirill или marina?",
    "Кто ровесники kseniya?",
    "Женился ли anna_laguta до 2005 года?",
    "kseniya и marina родные сестры/братья?",
    "kseniya бабушка vlad?",
    "У daria есть родные братья/сёстры?",
    "olga – свекровь anna?",
    "Перечислить браки, заключенные до 2000-го года",
    "roza – предок kseniya?",
    "Были ли anna и andrey когда-либо женаты?",
    "ariana старше denis?",
    "Перечислить всех людей, у которых нет родных братьев/сестер, и они младше 2005 года"
]

patterns = {
    r"Кто родители (.+)\?": FindParents,
    r"Кто дети (.+)\?": FindChildren,
    r"Кто братья и сестры (.+)\?": FindSiblings,
    r"Кто бабушки/дедушки (.+)\?": FindGrandparents,
    r"Кто старше: (.+) или (.+)\?": CompareAge,
    r"Кто ровесники (.+)\?": FindSameAge,
    r"Женился ли (.+) до (\d+) года\?": CheckMarriageBeforeYear,
    r"(.+) и (.+) родные сестры/братья\?": CheckSiblings,
    r"(.+) бабушка (.+)\?": CheckGrandparent,
    r"У (.+) есть родные братья/сёстры\?": CheckHasSiblings,
    r"(.+) – свекровь (.+)\?": CheckMotherInLaw,
    r"Перечислить браки, заключенные до (\d+)-го года": ListMarriagesBeforeYear,
    r"(.+) – предок (.+)\?": CheckAncestor,
    r"Были ли (.+) и (.+) когда-либо женаты\?": CheckWereMarried,
    r"(.+) старше (.+)\?": CheckOlder,
    r"Перечислить всех людей, у которых нет родных братьев/сестер, и они младше (\d+) года": ListPeopleWithoutSiblingsYoungerThan
}


def start():
    with PrologMQI() as mqi:
        with mqi.create_thread() as prolog:
            path = create_posix_path(KNOWLEDGE_BASE_PATH)
            prolog.query(f'consult("{path}")')

            print("Система готова к работе")
            print("Примеры запросов:", "\n * " + ("\n * ".join(requests)))
            print("Для завершения введите exit")

            while True:
                user_input = input("> ")
                if user_input.lower() == "exit":
                    break

                for pattern in patterns:
                    match = re.match(pattern, user_input, re.IGNORECASE)
                    if match is None:
                        continue

                    processor_class = patterns[pattern]
                    processor = processor_class(*match.groups())
                    processor.run(prolog)
                    break
                else:
                    print("One of us is a dumbass, follow request examples")


if __name__ == "__main__":
    start()
from tokenize import TokenInfo, OP, NEWLINE, NL
from typing import TYPE_CHECKING

from pylint.typing import MessageDefinitionTuple
from rulebook_pylint.checkers import TokenChecker
from rulebook_pylint.internals import Messages

if TYPE_CHECKING:
    from pylint.lint import PyLinter


class CodeBlockLineTrimmingChecker(TokenChecker):
    """See wiki: https://github.com/hendraanggrian/rulebook/wiki/Rules#code-block-line-trimming
    """
    MSG: str = 'code-block-line-trimming'

    name: str = 'code-block-line-trimming'
    msgs: dict[str, MessageDefinitionTuple] = Messages.of(MSG)

    def process_tokens(self, tokens: list[TokenInfo]) -> None:
        for i, token in enumerate(tokens):
            # first line of filter
            if token.type != OP or token.string != ':':
                continue

            # checks for violation
            if i + 2 >= len(tokens) or tokens[i + 1].type != NEWLINE or tokens[i + 2].type != NL:
                continue
            self.add_message(self.MSG, line=tokens[i + 2].start[0])


def register(linter: 'PyLinter') -> None:
    linter.register_checker(CodeBlockLineTrimmingChecker(linter))
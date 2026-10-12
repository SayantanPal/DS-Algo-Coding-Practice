export function valid_parenthesis_expression(s) {
  const parenthesesMap = { '(': ')', '{': '}', '[': ']' }
  const stack = []
  for (const c of s) {
    // If the current character is an opening parenthesis, push it onto the stack.
    if (c in parenthesesMap) {
      stack.push(c)
    } else {
      // If the current character is a closing parenthesis, check if it closes
      // the opening parenthesis at the top of the stack.
      if (stack.length && parenthesesMap[stack[stack.length - 1]] === c) {
        stack.pop()
      } else {
        return false
      }
    }
  }
  // If the stack is empty, all opening parentheses were successfully closed.
  return stack.length === 0
}
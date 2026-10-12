export function evaluate_expression(s) {
  const stack = []
  let currNum = 0,
    sign = 1,
    res = 0
  for (let c of s) {
    if (/\d/.test(c)) {
      currNum = currNum * 10 + parseInt(c)
    }
    // If the current character is an operator, add 'currNum' to the result
    // after multiplying it by its sign.
    else if (c === '+' || c === '-') {
      res += currNum * sign
      // Update the sign and reset 'currNum'.
      sign = c === '-' ? -1 : 1
      currNum = 0
    }
    // If the current character is an opening parenthesis, a new nested expression
    // is starting.
    else if (c === '(') {
      // Save the current 'res' and 'sign' values by pushing them onto
      // the stack, then reset their values to start calculating the new nested
      // expression.
      stack.push(res)
      stack.push(sign)
      res = 0
      sign = 1
    }
    // If the current character is a closing parenthesis, a nested expression has
    // ended.
    else if (c === ')') {
      // Finalize the result of the current nested expression.
      res += sign * currNum
      // Apply the sign of the current nested expression’s result before adding
      // this result to the result of the outer expression.
      res *= stack.pop()
      res += stack.pop()
      currNum = 0
    }
  }
  // Finalize the result of the overall expression.
  return res + currNum * sign
}
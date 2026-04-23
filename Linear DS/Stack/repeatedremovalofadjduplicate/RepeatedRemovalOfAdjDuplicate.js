export function repeated_removal_of_adjacent_duplicates(s) {
  const stack = []
  for (let c of s) {
    // If the current character is the same as the top character on the stack,
    // a pair of adjacent duplicates has been formed. So, pop the top character
    // from the stack.
    if (stack.length && c === stack[stack.length - 1]) {
      stack.pop()
    }
    // Otherwise, push the current character onto the stack.
    else {
      stack.push(c)
    }
  }
  // Return the remaining characters as a string.
  return stack.join('')
}
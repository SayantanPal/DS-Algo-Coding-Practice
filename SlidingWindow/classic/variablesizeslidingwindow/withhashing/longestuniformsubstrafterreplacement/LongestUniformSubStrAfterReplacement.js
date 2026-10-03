export function longest_uniform_substring_after_replacements(s, k) {
  const freqs = {}
  let highestFreq = 0
  let maxLen = 0
  let left = 0
  let right = 0
  while (right < s.length) {
    // Update the frequency of the character at the right pointer and the highest
    // frequency for the current window.
    const char = s[right]
    freqs[char] = (freqs[char] || 0) + 1
    highestFreq = Math.max(highestFreq, freqs[char])
    // Calculate replacements needed for the current window.
    const numCharsToReplace = right - left + 1 - highestFreq
    // Slide the window if the number of replacements needed exceeds 'k'.
    // The right pointer always gets advanced, so we just need to advance 'left'.
    if (numCharsToReplace > k) {
      freqs[s[left]] -= 1
      left += 1
    }
    // Since the length of the current window increases or stays the same, assign
    // the length of the current window to 'maxLen'.
    maxLen = right - left + 1
    // Expand the window.
    right += 1
  }
  return maxLen
}
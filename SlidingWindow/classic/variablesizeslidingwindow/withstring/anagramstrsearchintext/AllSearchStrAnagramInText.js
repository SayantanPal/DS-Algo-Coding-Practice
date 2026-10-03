export function substring_anagrams(s, t) {
  const len_s = s.length
  const len_t = t.length
  if (len_t > len_s) {
    return 0
  }
  let count = 0
  const expected_freqs = Array(26).fill(0)
  const window_freqs = Array(26).fill(0)
  // Populate 'expected_freqs' with the characters in string 't'.
  for (let c of t) {
    expected_freqs[c.charCodeAt(0) - 'a'.charCodeAt(0)] += 1
  }
  let left = 0
  let right = 0
  while (right < len_s) {
    // Add the character at the right pointer to 'window_freqs' before sliding the window.
    window_freqs[s.charCodeAt(right) - 'a'.charCodeAt(0)] += 1
    // If the window has reached the expected fixed length, we advance the left
    // pointer as well as the right pointer to slide the window.
    if (right - left + 1 === len_t) {
      if (arrays_equal(window_freqs, expected_freqs)) {
        count += 1
      }
      // Remove the character at the left pointer from 'window_freqs' before advancing the left pointer.
      window_freqs[s.charCodeAt(left) - 'a'.charCodeAt(0)] -= 1
      left += 1
    }
    right += 1
  }
  return count
}

function arrays_equal(arr1, arr2) {
  for (let i = 0; i < 26; i++) {
    if (arr1[i] !== arr2[i]) {
      return false
    }
  }
  return true
}
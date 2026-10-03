export function longest_substring_with_unique_chars(s) {
  let maxLen = 0
  const hashSet = new Set()
  let left = 0
  let right = 0
  while (right < s.length) {
    // If we encounter a duplicate character in the window, shrink the window until
    // it’s no longer a duplicate.
    while (hashSet.has(s[right])) {
      hashSet.delete(s[left])
      left += 1
    }

    // Once there are no more duplicates in the window, update 'maxLen' if the
    // current window is larger.
    maxLen = Math.max(maxLen, right - left + 1)
    hashSet.add(s[right])
    // Expand the window.
    right += 1
  }
  return maxLen
}

def longest_substring_with_unique_chars_optimized(s: str) -> int:
    max_len = 0
    prev_indexes = {}
    left = right = 0
    while right < len(s):
        # If a previous index of the current character is present in the current
        # window, it's a duplicate character in the window.
        if s[right] in prev_indexes and prev_indexes[s[right]] >= left:
            # Shrink the window to exclude the previous occurrence of this character.
            left = prev_indexes[s[right]] + 1
        # Update 'max_len' if the current window is larger.
        max_len = max(max_len, right - left + 1)
        prev_indexes[s[right]] = right
        # Expand the window.
        right += 1
    return max_len
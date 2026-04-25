export function zero_striping_hash_sets(matrix) {
  if (!matrix || !matrix.length || !matrix[0].length) {
    return
  }
  const m = matrix.length
  const n = matrix[0].length
  const zeroRows = new Set()
  const zeroCols = new Set()
  // Pass 1: Traverse through the matrix to identify the rows and columns
  // containing zeros and store their indexes in the appropriate hash sets.
  for (let r = 0; r < m; r++) {
    for (let c = 0; c < n; c++) {
      if (matrix[r][c] === 0) {
        zeroRows.add(r)
        zeroCols.add(c)
      }
    }
  }
  // Pass 2: Set any cell in the matrix to zero if its row index is in 'zeroRows'
  // or its column index is in 'zeroCols'.
  for (let r = 0; r < m; r++) {
    for (let c = 0; c < n; c++) {
      if (zeroRows.has(r) || zeroCols.has(c)) {
        matrix[r][c] = 0
      }
    }
  }
}

export function zero_striping(matrix) {
  if (!matrix || !matrix.length || !matrix[0].length) {
    return
  }
  const m = matrix.length
  const n = matrix[0].length
  // Check if the first row initially contains a zero.
  let firstRowHasZero = false
  for (let c = 0; c < n; c++) {
    if (matrix[0][c] === 0) {
      firstRowHasZero = true
      break
    }
  }
  // Check if the first column initially contains a zero.
  let firstColHasZero = false
  for (let r = 0; r < m; r++) {
    if (matrix[r][0] === 0) {
      firstColHasZero = true
      break
    }
  }
  // Use the first row and column as markers. If an element in the submatrix is zero,
  // mark its corresponding row and column in the first row and column as 0.
  for (let r = 1; r < m; r++) {
    for (let c = 1; c < n; c++) {
      if (matrix[r][c] === 0) {
        matrix[0][c] = 0
        matrix[r][0] = 0
      }
    }
  }
  // Update the submatrix using the markers in the first row and column.
  for (let r = 1; r < m; r++) {
    for (let c = 1; c < n; c++) {
      if (matrix[0][c] === 0 || matrix[r][0] === 0) {
        matrix[r][c] = 0
      }
    }
  }
  // If the first row had a zero initially, set all elements in the first row to
  // zero.
  if (firstRowHasZero) {
    for (let c = 0; c < n; c++) {
      matrix[0][c] = 0
    }
  }
  // If the first column had a zero initially, set all elements in the first column
  // to zero.
  if (firstColHasZero) {
    for (let r = 0; r < m; r++) {
      matrix[r][0] = 0
    }
  }
}
export function gas_stations(gas, cost) {
  // If total gas is less than total cost, we can't complete the circuit
  if (gas.reduce((a, b) => a + b, 0) < cost.reduce((a, b) => a + b, 0)) {
    return -1
  }
  let start = 0
  let tank = 0
  for (let i = 0; i < gas.length; i++) {
    tank += gas[i] - cost[i]
    // If tank is negative, we can't reach the next station
    if (tank < 0) {
      start = i + 1
      tank = 0
    }
  }
  return start
}
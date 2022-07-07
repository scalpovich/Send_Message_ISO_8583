export default function getField(){
const field = []
field.push({
    id: 2,
    name: "Primary Account Number",
    variable: true,
    length: 19,
    type: "an"
})

field.push({
    id: 3,
    name: "Processing Code",
    variable: false,
    length: 6,
    type: "n"
})

field.push({
    id: 3,
    name: "Processing Code",
    variable: false,
    length: 6,
    type: "n"
})

field.push({
    id: 4,
    name: "Transaction Amount",
    variable: false,
    length: 12,
    type: "n"
})

field.push({
    id: 4,
    name: "Transaction Amount",
    variable: false,
    length: 12,
    type: "n"
})

field.push({
    id: 5,
    name: "Settlement Amount",
    variable: false,
    length: 12,
    type: "n"
})

field.push({
    id: 6,
    name: "Cardholder Billing Amount",
    variable: false,
    length: 12,
    type: "n"
})

field.push({
    id: 7,
    name: "Transmission Date and Time (MMDDhhmmss)",
    variable: false,
    length: 10,
    type: "n"
})

field.push({
    id: 9,
    name: "Settlement Conversion Rate",
    variable: false,
    length: 8,
    type: "n"
})

field.push({
    id: 10,
    name: "Cardholder Billing Conversion Rate",
    variable: false,
    length: 8,
    type: "n"
})

field.push({
    id: 11,
    name: "System Trace Audit Number",
    variable: false,
    length: 6,
    type: "n"
})

field.push({
    id: 12,
    name: "Local Transaction Time (hhmmss)",
    variable: false,
    length: 6,
    type: "n"
})

field.push({
    id: 13,
    name: "Local Transaction Date (MMDD)",
    variable: false,
    length: 4,
    type: "n"
})

field.push({
    id: 14,
    name: "Expiration Date (YYMM)",
    variable: false,
    length: 4,
    type: "n"
})

field.push({
    id: 15,
    name: "Settlement Date (MMDD)",
    variable: false,
    length: 4,
    type: "n"
})

field.push({
    id: 18,
    name: "Merchant Category Code",
    variable: false,
    length: 4,
    type: "n"
})

field.push({
    id: 19,
    name: "Acquiring Institution Country Code",
    variable: false,
    length: 3,
    type: "n"
})

field.push({
    id: 22,
    name: "Point-of-Service Entry Mode",
    variable: false,
    length: 3,
    type: "n"
})

field.push({
    id: 23,
    name: "Card Sequence Number",
    variable: false,
    length: 3,
    type: "n"
})

field.push({
    id: 25,
    name: "Point-of-Service Condition Code ",
    variable: false,
    length: 2,
    type: "n"
})

field.push({
    id: 26,
    name: "Point-of-Service PIN Capture Code",
    variable: false,
    length: 2,
    type: "n"
})

field.push({
    id: 32,
    name: "Acquiring Instititution Code",
    variable: true,
    length: 11,
    type: "n"
})

field.push({
    id: 35,
    name: "Track-2 Data",
    variable: true,
    length: 37,
    type: "z"
})

field.push({
    id: 36,
    name: "Track-3 Data",
    variable: true,
    length: 37,
    type: "z"
})

field.push({
    id: 37,
    name: "Retrieval reference number",
    variable: false,
    length: 12,
    type: "an"
})

field.push({
    id: 38,
    name: "Authorization identification response",
    variable: false,
    length: 6,
    type: "ans"
})

field.push({
    id: 39,
    name: "Respone Code",
    variable: false,
    length: 2,
    type: "an"
})

field.push({
    id: 41,
    name: "Card Acceptor Terminal Identification",
    variable: false,
    length: 8,
    type: "ans"
})

field.push({
    id: 42,
    name: "Card Acceptor Identification Code",
    variable: false,
    length: 15,
    type: "ans"
})

field.push({
    id: 43,
    name: "Card Acceptor Name/Location",
    variable: false,
    length: 40,
    type: "ans"
})

field.push({
    id: 45,
    name: "Track-1 Data",
    variable: true,
    length: 79,
    type: "ans"
})

field.push({
    id: 48,
    name: "Additional private data",
    variable: true,
    length: 999,
    type: "ans"
})

field.push({
    id: 49,
    name: "Currency Code, Transaction",
    variable: false,
    length: 3,
    type: "n"
})

field.push({
    id: 50,
    name: "Settlement Currency Code",
    variable: false,
    length: 3,
    type: "n"
})

field.push({
    id: 51,
    name: "Cardholder Billing Currency Code",
    variable: false,
    length: 3,
    type: "n"
})

field.push({
    id: 52,
    name: "Personal Identification Number (PIN) Data",
    variable: false,
    length: 16,
    type: "an"
})

field.push({
    id: 54,
    name: "Additional amount",
    variable: true,
    length: 120,
    type: "an"
})

field.push({
    id: 55,
    name: "Chip Data",
    variable: true,
    length: 225,
    type: "b"
})

field.push({
    id: 60,
    name: "Self – defined Field",
    variable: true,
    length: 60,
    type: "ans"
})

field.push({
    id: 62,
    name: "Service Code",
    variable: true,
    length: 10,
    type: "ans"
})

field.push({
    id: 63,
    name: "Transaction Reference Number",
    variable: true,
    length: 16,
    type: "ans"
})

field.push({
    id: 70,
    name: "Network Management Information Code",
    variable: false,
    length: 3,
    type: "n"
})

field.push({
    id: 90,
    name: "Original Data Elements",
    variable: false,
    length: 42,
    type: "n"
})

field.push({
    id: 100,
    name: "Receiving Institution Identification Code",
    variable: true,
    length: 11,
    type: "n"
})

field.push({
    id: 102,
    name: "Account Identification-1",
    variable: true,
    length: 28,
    type: "an"
})

field.push({
    id: 103,
    name: "Account Identification-2",
    variable: true,
    length: 28,
    type: "an"
})

field.push({
    id: 104,
    name: "Content transfer",
    variable: true,
    length: 210,
    type: "ans"
})

field.push({
    id: 105,
    name: "New PIN Block",
    variable: true,
    length: 999,
    type: "ans"
})

field.push({
    id: 120,
    name: "Benificial Cardholder Or Account Holder Information",
    variable: true,
    length: 70,
    type: "ans"
})

field.push({
    id: 128,
    name: "Message Authentication Code",
    variable: false,
    length: 16,
    type: "an"
})

return field

}

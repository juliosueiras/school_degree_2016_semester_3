(* Author: Julio Tain Sueiras *)

let binaryNo = ref 0
let remainder = ref 0
let factor = ref 1

let rec toBinary decimalNo =
    match decimalNo with
    | 0 ->
        binaryNo
    | _ ->
        remainder := decimalNo % 2
        binaryNo := !binaryNo + !remainder * !factor
        factor := !factor * 10
        toBinary(decimalNo / 2)

let result = toBinary 2
printf "Result:%d" !result

(* Testing *)
open System

for a in 1 .. 30 do
    (* Reset Value *)
    binaryNo := 0
    remainder := 0
    factor := 1

    let actual = !(toBinary a)
    let expected = Convert.ToInt32(Convert.ToString(a, 2))

    if actual <> expected then
        printfn "A Test has failed: Current: %d, Actual: %d, Expected: %d" a actual expected



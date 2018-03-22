import json, math

def CreateHammingCode(RawData, Show = False):

	Data = []
	DataIterator = 0
	ActualPow = 0
	RawDataIterator = 0

	while True:
		if (DataIterator + 1) == (2**ActualPow):
			Data += "X"
			ActualPow += 1 
			DataIterator += 1
		else:
			Data += RawData[RawDataIterator]
			RawDataIterator += 1
			DataIterator += 1 

		if (RawDataIterator == len(RawData)): 
			break

	if Show: print(f"Parity Bits: {''.join(Data)}")
	if Show: print(f"Parity Bits: {ActualPow}")

	for ParityBit in range(0, ActualPow):

		CounterOf1 = 0
		for Bit, BitValue in enumerate(Data):
			if (Bit + 1) >> ParityBit & 1 == 1:
				if (Bit != (2**ParityBit - 1)):
					if (Data[Bit] == "1"): CounterOf1 += 1

		if (CounterOf1 % 2 == 0):
			Data[(2**ParityBit - 1)] = "0" 
		else:
			Data[(2**ParityBit - 1)] = "1"

	return ''.join(Data)

def CheckErrorHammingCode(Data, Show = False):

	ErrorAdress = 0
	NumberOfParityBits = math.ceil(math.log2(len(Data)))

	for ParityBit in range(0, NumberOfParityBits):
		CounterOf1 = 0

		for Bit, BitValue in enumerate(Data):
			if (Bit + 1) >> ParityBit & 1 == 1:
				if (Data[Bit] == "1"): CounterOf1 += 1

		if (CounterOf1 % 2 != 0):
			ErrorAdress += 2**(ParityBit)

	if Show: print(f"Error Adress: {ErrorAdress}")

	if ErrorAdress == 0: 
		return Data

	Data = list(Data)

	if Data[ErrorAdress - 1] == "1":
		Data[ErrorAdress - 1] = "0"
	else:
		Data[ErrorAdress - 1] = "1" 

	return ''.join(Data)	

with open('HammingData.json', encoding='utf-8') as DataFile:
    HammingData = json.loads(DataFile.read())

    RawData = HammingData["ToEncodeByHamming"]
    DataToCheck = HammingData["ToCheckByHamming"]

    print(CreateHammingCode(RawData, True))
    print(CheckErrorHammingCode(DataToCheck, True))
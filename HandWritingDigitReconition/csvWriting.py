import csv

with open('Data.csv', mode='w') as csv_file:
    fieldnames = ['label']
    for i in range(0,28*28):
        fieldnames.append('pixel'+str(i))
    writer = csv.DictWriter(csv_file, fieldnames=fieldnames)
    writer.writeheader()

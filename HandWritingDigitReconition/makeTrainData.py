import numpy as np
import matplotlib.pyplot as plt
import csv

fieldnames = ['label']
for i in range(0,28*28):
    fieldnames.append('pixel'+str(i))
data={}

def showDigit(X):
    X_images = X.reshape(28, 28)
    plt.imshow(X_images)
    plt.show()
def writeToFile(x,y):
    with open('Data.csv', mode='a') as csv_file:
        data['label']=y
        for i in range(0, 28 * 28):
            data['pixel' + str(i)]=x[0][i]
        writer = csv.DictWriter(csv_file,fieldnames=fieldnames)
        writer.writerow(data)

while (True):
    try:
        y=np.loadtxt("D:\JavaProject\CalculatorProject\Label.txt", dtype='S', delimiter=' ')
        x_test = np.loadtxt("D:\JavaProject\CalculatorProject\Java.txt", dtype='f', delimiter=' ')
    finally:
        if (x_test.shape[0] == 0):
            continue
    xt = np.asarray(x_test)
    xt = xt.reshape(1, -1)
    writeToFile(xt,y)
    print(y)
    # showDigit(xt[0])

    file = open('D:\JavaProject\CalculatorProject\Java.txt', 'w')
    file.write('')
    file.close()
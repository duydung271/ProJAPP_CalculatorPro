import numpy as np
import pickle
import matplotlib.pyplot as plt
loaded_model = pickle.load(open('handwriteDigitReconizge_model.sav', 'rb'))

def showDigit(X):
    X_images = X.reshape(28, 28)
    plt.imshow(X_images)
    plt.show()

while (True):
    try:
        x_test = np.loadtxt("D:\JavaProject\CalculatorProject\Java.txt", dtype='f', delimiter=' ')
    finally:
        if (x_test.shape[0] == 0):
            continue
    file = open('D:\JavaProject\CalculatorProject\Java.txt', 'w')
    file.write('')
    file.close()

    xt = np.asarray(x_test)
    xt = xt.reshape(1, -1)
    # showDigit(xt[0])
    y_pred = loaded_model.predict(xt);

    file = open('D:\JavaProject\CalculatorProject\Python.txt', 'w')
    file.write(y_pred[0][-2])
    file.close()
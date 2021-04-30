import numpy as np # linear algebra
import pandas as pd
import matplotlib.pyplot as plt
train_df = pd.read_csv('Data.csv')
print(train_df)
train_df['label'].value_counts().plot(kind='bar')
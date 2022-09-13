from sentence_transformers import SentenceTransformer 
modelPath = '/usr/src/models'

model = SentenceTransformer('paraphrase-multilingual-MiniLM-L12-v2') # Download the model
model.save(modelPath)   # Save model locally

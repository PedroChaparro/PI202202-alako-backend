from bottle import post, run, response, request # Api packages

from sentence_transformers import SentenceTransformer # Vectorize sentences
model = SentenceTransformer('paraphrase-multilingual-MiniLM-L12-v2') # Download the model

@post('/vectorize')
def index():
    payload = request.json # Get json from req.body
    response.content_type = 'applicattion/json'

    # Simple validation
    if(payload and 'criteria' in payload):
        vector = model.encode(payload['criteria']).tolist() # Encode and convert to list
        response.status = 200
        return {'error': False, 'message': 'OK', 'vector': vector}
    else:
        response.status = 400
        return {'error': True, 'message': 'Criteria string was not provided'}

run(host = 'localhost', port='8080', debug=True)
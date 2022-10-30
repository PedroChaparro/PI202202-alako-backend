from bottle import post, run, response, request # Api packages

from sentence_transformers import SentenceTransformer # Vectorize sentences
# model = SentenceTransformer('paraphrase-multilingual-MiniLM-L12-v2') # Download the model
model = SentenceTransformer('/usr/src/models') # Use local model (Docker image only)

@post('/vectorize')
def index():
    payload = request.json # Get json from req.body
    response.content_type = 'applicattion/json'

    # Simple validation
    if(payload and 'search-criteria' in payload):
        vector = model.encode(payload['search-criteria']).tolist() # Encode and convert to list
        response.status = 200
        return {'error': False, 'message': 'OK', 'vector': vector}
    else:
        response.status = 400
        return {'error': True, 'message': 'Criteria string was not provided'}

run(host = '0.0.0.0', port='5050', debug=True)

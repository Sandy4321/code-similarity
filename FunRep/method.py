import json
import re
from collections import Counter
from FunRep.lang import meaningful_tokens

class MethodFeatureVector:
    """ A method object represents Java Methods parsed via the featureExtractor.jar 
        This class should implement the bare bones needed for a baseline.
    """
    
    def __init__(self, jsonString):
        """ Always initialize from JSON strings like {key1 : value1, key2: value2..}"""
        jsonObj = json.loads(jsonString.strip(), strict=False)
        
        self.name = jsonObj['name']
        if 'className' in jsonObj:
            self.className = jsonObj['className']
        self.is_empty = jsonObj['isEmpty']

        # pure text form of the method
        self.raw_text = jsonObj['text']
        
        # extracted features 
        self.features = {}

        # String or numeric features
        self.features['returnType'] = str(jsonObj['returnType'])
        self.features['modifier'] = int(jsonObj['modifier'])
        self.features['lineCount'] = int(jsonObj['lineCount']) 
        if 'javaDoc' in jsonObj:
            self.features['javaDoc'] = str(jsonObj['javaDoc']).lower()
        if 'comments' in jsonObj:
            self.features['comments'] = str(jsonObj['comments']).lower()
        
        # List features
        self.features['paramTypes'] = list(jsonObj['paramTypes'])
        
        # set features, where we consider only if present or absent
        self.features['exceptions'] = set(jsonObj['exceptions'])
        self.features['annotations'] = set(jsonObj['annotations'])
        self.features['concepts'] = set(jsonObj['concepts'])
        self.features['methodCalls'] = set(jsonObj['methodCalls'])
        self.features['variables'] = set(jsonObj['variables'])
        self.features['constants'] = set(jsonObj['constants'])
        
        # Where counts matter, we use counter objects
        self.features['expressions'] = Counter(jsonObj['expressions'])
        self.features['statements'] = Counter(jsonObj['statements'])
        self.features['types'] = Counter(jsonObj['types'])
        
        camelCaseSplitText = re.sub(r'((?<=[a-z])[A-Z]|(?<!\A)[A-Z](?=[a-z]))', r' \1', self.raw_text)
        self.tokens = meaningful_tokens(camelCaseSplitText)

